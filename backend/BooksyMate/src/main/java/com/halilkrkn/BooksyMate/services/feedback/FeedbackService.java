package com.halilkrkn.BooksyMate.services.feedback;

import com.halilkrkn.BooksyMate.dto.request.feedback.FeedbackRequest;
import com.halilkrkn.BooksyMate.dto.response.book.PageResponse;
import com.halilkrkn.BooksyMate.dto.response.feedback.FeedbackResponse;
import org.springframework.security.core.Authentication;

public interface FeedbackService {

    Integer saveFeedback(FeedbackRequest request, Authentication connectedUser);

    PageResponse<FeedbackResponse> findAllFeedbacksByBook(Integer bookId, Integer page, Integer size, Authentication connectedUser);
}
