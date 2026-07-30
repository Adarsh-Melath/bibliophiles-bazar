package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.domain.exception.BookNotFoundException;
import com.adarsh.backend.feature.book.domain.exception.constant.BookExceptionMessageConstants;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.category.application.port.CategoryQueryPort;
import com.adarsh.backend.feature.category.domain.model.Category;
import com.adarsh.backend.feature.discount.application.dto.result.GetActiveOfferForBookResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.port.OfferQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.GetActiveOfferForBookUseCase;
import com.adarsh.backend.feature.discount.domain.model.Offer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetActiveOfferForBookInteractor implements GetActiveOfferForBookUseCase {

    private static final Logger logger = LoggerFactory.getLogger(GetActiveOfferForBookInteractor.class);

    private final BookQueryRepositoryPort bookQueryRepositoryPort;
    private final CategoryQueryPort categoryQueryPort;
    private final OfferQueryRepositoryPort offerQueryRepositoryPort;

    @Override
    public GetActiveOfferForBookResult execute(Long bookId) {
        logger.info(DiscountInteractorLogConstants.GET_ACTIVE_OFFER_FOR_BOOK_REQUEST, bookId);

        Book book = bookQueryRepositoryPort.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(BookExceptionMessageConstants.BOOK_NOT_FOUND));

        Long categoryId = null;
        if (book.getCategory() != null) {
            Optional<Category> categoryOpt = categoryQueryPort.findByType(book.getCategory());
            if (categoryOpt.isPresent()) {
                categoryId = categoryOpt.get().getId();
            }
        }

        List<Offer> activeOffers = offerQueryRepositoryPort.findActiveOffersForBookAndCategory(bookId, categoryId);

        Offer bestOffer = activeOffers.stream()
                .filter(Offer::isValid)
                .max(Comparator.comparingDouble(Offer::getDiscountPercentage))
                .orElse(null);

        return GetActiveOfferForBookResult.fromDomain(bestOffer);
    }
}
