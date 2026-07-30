package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.domain.exception.BookNotFoundException;
import com.adarsh.backend.feature.book.domain.exception.constant.BookExceptionMessageConstants;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.discount.application.dto.command.CreateProductOfferCommand;
import com.adarsh.backend.feature.discount.application.dto.result.CreateOfferResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorMessageConstants;
import com.adarsh.backend.feature.discount.application.port.OfferCommandRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.CreateProductOfferUseCase;
import com.adarsh.backend.feature.discount.domain.exception.UnauthorizedOfferAccessException;
import com.adarsh.backend.feature.discount.domain.exception.constant.DiscountExceptionMessageConstants;
import com.adarsh.backend.feature.discount.domain.model.Offer;
import com.adarsh.backend.feature.discount.domain.model.OfferType;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateProductOfferInteractor implements CreateProductOfferUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateProductOfferInteractor.class);

    private final UserQueryRepository userQueryRepository;
    private final BookQueryRepositoryPort bookQueryRepositoryPort;
    private final OfferCommandRepositoryPort offerCommandRepositoryPort;

    @Override
    @Transactional
    public CreateOfferResult execute(String email, CreateProductOfferCommand command) {
        logger.info(DiscountInteractorLogConstants.CREATE_PRODUCT_OFFER_REQUEST, command.bookId(), email);

        User publisher = userQueryRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        Book book = bookQueryRepositoryPort.findById(command.bookId())
                .orElseThrow(() -> new BookNotFoundException(BookExceptionMessageConstants.BOOK_NOT_FOUND));

        if (book.getPublisherId() != null && !book.getPublisherId().equals(publisher.getId())) {
            throw new UnauthorizedOfferAccessException(DiscountExceptionMessageConstants.UNAUTHORIZED_OFFER_ACCESS);
        }

        String offerName = command.name() != null && !command.name().trim().isEmpty()
                ? command.name().trim()
                : String.format(DiscountInteractorMessageConstants.DEFAULT_PRODUCT_OFFER_NAME, book.getTitle());

        Offer newOffer = new Offer.Builder()
                .name(offerName)
                .offerType(OfferType.PRODUCT)
                .targetId(command.bookId())
                .publisherId(publisher.getId())
                .discountPercentage(command.discountPercentage())
                .startDate(command.startDate())
                .endDate(command.endDate())
                .isActive(true)
                .build();

        Offer savedOffer = offerCommandRepositoryPort.save(newOffer);
        return CreateOfferResult.fromDomain(savedOffer);
    }
}
