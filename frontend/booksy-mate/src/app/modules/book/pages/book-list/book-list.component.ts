import {Component, OnInit} from '@angular/core';
import {BookService} from '../../../../services/services/book.service';
import {Router} from '@angular/router';
import {PageResponseBookResponse} from '../../../../services/models/page-response-book-response';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-book-list',
  imports: [
    NgForOf
  ],
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.scss'
})
export class BookListComponent implements OnInit {
  protected bookResponse: PageResponseBookResponse = {};
  private page: number = 0;
  private size: number = 10;

  constructor(
    private router: Router,
    private bookService: BookService,
  ) {}

  ngOnInit(): void {
    this.findAllBooks();
  }

  private findAllBooks() {
    this.bookService.findAllBooks({
      page: this.page,
      size: this.size,
    }).subscribe({
      next: (books: PageResponseBookResponse) => {
        this.bookResponse = books;
      }
    });
  }
}
