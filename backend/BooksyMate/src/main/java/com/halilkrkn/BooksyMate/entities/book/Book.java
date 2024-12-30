package com.halilkrkn.BooksyMate.entities.book;

import com.halilkrkn.BooksyMate.entities.BaseEntity;
import com.halilkrkn.BooksyMate.entities.feedback.FeedBack;
import com.halilkrkn.BooksyMate.entities.history.BookTransactionHistory;
import com.halilkrkn.BooksyMate.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntity {

    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<FeedBack> feedBacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

    @Transient
    public double getRate() {

        if (feedBacks == null || feedBacks.isEmpty()) {
            return 0.0;
        }

        var rate = this.feedBacks.stream()
                .mapToDouble(FeedBack::getNote)
                .average()
                .orElse(0.0);

        double roundedRate = Math.round(rate * 10.0) / 10.0;

        return roundedRate;
    }

}
