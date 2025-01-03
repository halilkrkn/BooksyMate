package com.halilkrkn.BooksyMate.controllers.feedback;

import com.halilkrkn.BooksyMate.dto.request.feedback.FeedbackRequest;
import com.halilkrkn.BooksyMate.dto.response.book.PageResponse;
import com.halilkrkn.BooksyMate.dto.response.feedback.FeedbackResponse;
import com.halilkrkn.BooksyMate.services.feedback.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback", description = "Feedback endpoints")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping()
    public ResponseEntity<Integer> saveFeedback(
            @RequestBody @Valid FeedbackRequest request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(
                feedbackService.saveFeedback(request, connectedUser)
        );
    }

    @GetMapping("/book/{book-id}")
    public ResponseEntity<PageResponse<FeedbackResponse>> findAllFeedbacksByBook(
            @PathVariable("book-id") Integer bookId,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(
                feedbackService.findAllFeedbacksByBook(bookId, page, size, connectedUser)
        );
    }



}
