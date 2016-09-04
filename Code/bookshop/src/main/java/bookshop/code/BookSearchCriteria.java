package bookshop.code;

public class BookSearchCriteria
{
	private Author author;
	private String title;
	private Genre genre;
	private Integer upPrice;
	private Integer lowPrice;

	public Author getAuthor()
	{
		return author;
	}

	public void setAuthor(Author author)
	{
		this.author = author;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}

	public Integer getUpPrice()
	{
		return upPrice;
	}

	public void setUpPrice(Integer upPrice)
	{
		this.upPrice = upPrice;
	}

	public Integer getLowPrice()
	{
		return lowPrice;
	}

	public void setLowPrice(Integer lowPrice)
	{
		this.lowPrice = lowPrice;
	}

}
