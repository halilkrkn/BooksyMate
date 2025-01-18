import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RatingComponent} from '../../components/rating/rating.component';
import {RouterLink} from '@angular/router';
import {BorrowedBookResponse} from '../../../../services/models/borrowed-book-response';
import {PageResponseBorrowedBookResponse} from '../../../../services/models/page-response-borrowed-book-response';
import {BookResponse} from '../../../../services/models/book-response';
import {FeedbackRequest} from '../../../../services/models/feedback-request';
import {BookService} from '../../../../services/services/book.service';
import {FeedbackService} from '../../../../services/services/feedback.service';

@Component({
  selector: 'app-borrowed-books',
  imports: [
    NgIf,
    FormsModule,
    RatingComponent,
    // RouterLink,
    NgForOf
  ],
  templateUrl: './borrowed-books.component.html',
  styleUrl: './borrowed-books.component.scss'
})
export class BorrowedBooksComponent implements OnInit {

  borrowedBookResponse: PageResponseBorrowedBookResponse = {};
  selectedBook: BorrowedBookResponse | undefined = undefined;
  feedbackRequest: FeedbackRequest = {bookId: 0, comment: "", note: 0};
  page = 0;
  size = 10;
  pages: any = [];

  constructor(
    private bookService: BookService,
    private feedbackService: FeedbackService
  ) {}

  ngOnInit(): void {
    this.findAllBorrowedBooks();
  }

  private findAllBorrowedBooks() {
    this.bookService.findAllBorrowedBooks({
        page: this.page,
        size: this.size
      }
    ).subscribe({
      next: (response: PageResponseBorrowedBookResponse) => {
        this.borrowedBookResponse = response;
        this.pages = Array(this.borrowedBookResponse.totalPages)
          .fill(0)
          .map((x, i) => i);
      }
    })
  }

  returnBorrowedBook(book: BorrowedBookResponse) {
    this.selectedBook = book;
    this.feedbackRequest.bookId = book.id as number;
  }

  returnBook(withFeedback: boolean) {
    this.bookService.returnBorrowBook({
      'book-id': this.selectedBook?.id as number
    }).subscribe({
      next: () => {
        if (withFeedback) {
          this.giveFeedback();
        }
        this.selectedBook = undefined;
        this.findAllBorrowedBooks();
      }
    })
  }

  private giveFeedback() {
    this.feedbackService.saveFeedback({
      body: this.feedbackRequest
    }).subscribe({
      next: () => {}
    })
  }


  gotToPage(page: number) {
    this.page = page;
    this.findAllBorrowedBooks();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllBorrowedBooks();
  }

  goToPreviousPage() {
    this.page--;
    this.findAllBorrowedBooks();
  }

  goToLastPage() {
    this.page = this.borrowedBookResponse.totalPages as number - 1;
    this.findAllBorrowedBooks();
  }

  goToNextPage() {
    this.page++;
    this.findAllBorrowedBooks();
  }

  get isLastPage() {
    return this.page === this.borrowedBookResponse.totalPages as number - 1;
  }
}
