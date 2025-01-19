package com.halilkrkn.BooksyMate.core.mapper.feedback;

import com.halilkrkn.BooksyMate.dto.request.feedback.FeedbackRequest;
import com.halilkrkn.BooksyMate.dto.response.feedback.FeedbackResponse;
import com.halilkrkn.BooksyMate.entities.book.Book;
import com.halilkrkn.BooksyMate.entities.feedback.Feedback;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedbackMapper {
    public Feedback toFeedback(FeedbackRequest request) {
        return Feedback.builder()
                .note(request.note())
                .comment(request.comment())
                .book(Book.builder()
                        .id(request.bookId())
                        .archived(false) // Not required and has no impact :: just to satisfy the lombok
                        .shareable(false) // Not required and has no impact :: just to satisfy the lombok
                        .build()
                )
                .build();
    }

    public FeedbackResponse toFeedbackResponse(Feedback feedback, Integer userId) {
        return FeedbackResponse.builder()
                .note(feedback.getNote())
                .comment(feedback.getComment())
                .ownFeedback(Objects.equals(feedback.getCreatedBy(), userId))
                .build();
    }
}
