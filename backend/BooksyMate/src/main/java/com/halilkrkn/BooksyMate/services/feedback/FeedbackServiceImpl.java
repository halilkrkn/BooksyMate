package com.halilkrkn.BooksyMate.services.feedback;

import com.halilkrkn.BooksyMate.core.exception.OperationNotPermittedException;
import com.halilkrkn.BooksyMate.core.mapper.feedback.FeedbackMapper;
import com.halilkrkn.BooksyMate.dto.request.feedback.FeedbackRequest;
import com.halilkrkn.BooksyMate.dto.response.book.PageResponse;
import com.halilkrkn.BooksyMate.dto.response.feedback.FeedbackResponse;
import com.halilkrkn.BooksyMate.entities.feedback.Feedback;
import com.halilkrkn.BooksyMate.entities.user.User;
import com.halilkrkn.BooksyMate.repositories.book.BookRepository;
import com.halilkrkn.BooksyMate.repositories.feedback.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;

    @Override
    public Integer saveFeedback(FeedbackRequest request, Authentication connectedUser) {
        var book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("No book found with id: " + request.bookId()));

        if(book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot give feedback to an archived or unshareable book.");
        }

        var user = ((User) connectedUser.getPrincipal());
        if(!Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot give feedback to a book that you do not own.");
        }

        var feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }

    @Override
    public PageResponse<FeedbackResponse> findAllFeedbacksByBook(Integer bookId, Integer page, Integer size, Authentication connectedUser) {

        Pageable pageable = PageRequest.of(page, size);
        var user = ((User) connectedUser.getPrincipal());

        Page<Feedback> feedbacks = feedbackRepository.findAllByBookId(bookId, pageable);
        List<FeedbackResponse> feedbackResponses = feedbacks.stream()
                .map(feedback -> feedbackMapper.toFeedbackResponse(feedback, user.getId()))
                .toList();

        return new PageResponse<FeedbackResponse>(
                feedbackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );

    }
}
