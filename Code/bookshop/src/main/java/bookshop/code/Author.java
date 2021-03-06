package bookshop.code;
// Generated 08.04.2016 16:24:57 by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Author generated by hbm2java
 */
@Entity
@Table(name = "author", catalog = "bookshop")
public class Author implements java.io.Serializable
{

	private Integer id;
	private String FName;
	private String LName;
	private String MName;
	private Set<BookToAuthor> bookToAuthors = new HashSet<BookToAuthor>(0);
	private Set<Book> books = new HashSet<Book>(0);

	public Author()
	{
	}

	public Author(String FName, String LName, String MName, Set<BookToAuthor> bookToAuthors, Set<Book> books)
	{
		this.FName = FName;
		this.LName = LName;
		this.MName = MName;
		this.bookToAuthors = bookToAuthors;
		this.books = books;
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

	@Column(name = "f_name", length = 40)
	public String getFName()
	{
		return this.FName;
	}

	public void setFName(String FName)
	{
		this.FName = FName;
	}

	@Column(name = "l_name", length = 40)
	public String getLName()
	{
		return this.LName;
	}

	public void setLName(String LName)
	{
		this.LName = LName;
	}

	@Column(name = "m_name", length = 40)
	public String getMName()
	{
		return this.MName;
	}

	public void setMName(String MName)
	{
		this.MName = MName;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
	public Set<BookToAuthor> getBookToAuthors()
	{
		return this.bookToAuthors;
	}

	@Transient
	public String toString()
	{
		String s = new String();
		if (this.FName != null)
		{
			s += this.FName;
			s += " ";
		}
		if (this.MName != null)
		{
			s += this.MName;
			s += " ";
		}
		if (this.LName != null)
		{
			s += this.LName;
		}
		return s;
	}

	public void setBookToAuthors(Set<BookToAuthor> bookToAuthors)
	{
		this.bookToAuthors = bookToAuthors;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "book_to_author", catalog = "bookshop", joinColumns = {
			@JoinColumn(name = "author_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "book_id", nullable = false, updatable = false) })
	public Set<Book> getBooks()
	{
		return this.books;
	}

	public void setBooks(Set<Book> b)
	{
		this.books = b;
	}
}
