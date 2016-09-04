package bookshop.code;
// Generated 15.04.2016 15:53:53 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PublHouse generated by hbm2java
 */
@Entity
@Table(name = "publ_house", catalog = "bookshop")
public class PublHouse implements java.io.Serializable
{

	private Integer id;
	private String name;
	private Set<Book> books = new HashSet<Book>(0);

	public PublHouse()
	{
	}

	public PublHouse(String name, Set<Book> books)
	{
		this.name = name;
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

	@Column(name = "name", length = 40)
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "publHouse")
	public Set<Book> getBooks()
	{
		return this.books;
	}

	public void setBooks(Set<Book> books)
	{
		this.books = books;
	}

}