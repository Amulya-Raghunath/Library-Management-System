package com.example.library.repositories;

import com.example.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>
{
    List<Book> findByTitleContainingIgnoreCase(String title);

    // 🔍 Enhanced search: title, author, or category name
    @Query("SELECT b FROM Book b " +
            "JOIN b.author a " +
            "JOIN b.category c " +
            "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "   OR LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "   OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchByTitleOrAuthorOrCategory(@Param("keyword") String keyword);
}
