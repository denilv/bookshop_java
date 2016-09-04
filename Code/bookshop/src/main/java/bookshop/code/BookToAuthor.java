package bookshop.code;
// Generated 15.04.2016 15:53:53 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * BookToAuthor generated by hbm2java
 */
@Entity
@Table(name = "book_to_author", catalog = "bookshop")
public class BookToAuthor implements java.io.Serializable
{

	private Integer id;
	private Author author;
	private Book book;

	public BookToAuthor()
	{
	}

	public BookToAuthor(Author author, Book book)
	{
		this.author = author;
		this.book = book;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id")
	public Author getAuthor()
	{
		return this.author;
	}

	public void setAuthor(Author author)
	{
		this.author = author;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id")
	public Book getBook()
	{
		return this.book;
	}

	public void setBook(Book book)
	{
		this.book = book;
	}

}
