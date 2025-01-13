import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from '@angular/common';
import {BookRequest} from '../../../../services/models/book-request';
import {FormsModule} from '@angular/forms';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {BookService} from '../../../../services/services/book.service';
import {BookResponse} from '../../../../services/models/book-response';

@Component({
  selector: 'app-manage-book',
  imports: [
    NgForOf,
    NgIf,
    FormsModule,
    RouterLink
  ],
  templateUrl: './manage-book.component.html',
  styleUrl: './manage-book.component.scss'
})
export class ManageBookComponent implements OnInit {

  bookRequest: BookRequest = {authorName: '', isbn: '', synopsis: '', title: ''};
  errorMessage: Array<string> = [];
  selectedBookCover: any;
  selectedPicture: string | undefined;

  constructor(
    private bookService: BookService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}


  // Buradaki olay edit tuşuna basıldığında kitabın bilgilerini getirip formda göstermek
  ngOnInit(): void {
    const bookId = this.activatedRoute.snapshot.params['bookId'];

    if (bookId) {
      this.bookService.findBookById({
        "book-id": bookId
      }).subscribe({
        next: (book: BookResponse) => {
          this.bookRequest = {
            id: book.id,
            title: book.title as string,
            authorName: book.authorName as string,
            isbn: book.isbn as string,
            synopsis: book.synopsis as string,
            shareable: book.shareable as boolean
          }
          if (book.cover) {
            this.selectedPicture = 'data:image/jpg;base64,' + book.cover;
          }
        }
      });
    }
  }

  onFileSelected(event: any) {
    this.selectedBookCover = event.target.files[0];
    console.log(this.selectedBookCover);

    if (this.selectedBookCover) {
      const reader = new FileReader();
      reader.onload = () => {
        this.selectedPicture = reader.result as string;
      }
      reader.readAsDataURL(this.selectedBookCover);
    }
  }

  saveBook() {
    this.bookService.saveBook({
      body: this.bookRequest,
    }).subscribe({
      next:(bookId: number) => {
        this.bookService.uploadBookCoverPicture({
          "book-id": bookId,
          body: {
            file: this.selectedBookCover
          }
        }).subscribe({
          next:() => {
            this.router.navigate(['/books/my-books']);
          }
        })
      },
      error: (err) => {
        this.errorMessage = err.error.validationErrors;
      }
    })
  }
}
