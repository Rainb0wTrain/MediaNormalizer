import java.nio.file.Path;

public class BookInfo extends MediaInfo{
    private final String author;


    public BookInfo(String title, String extension, String author) {
        super(title, extension);
        this.author = author;
    }

    @Override
    public Path getJellyfinPath() {
        //folder author
        //folder book title + (year later if wanted)
        //book title "." extension (epub)

        return Path.of(author, title, title + "." + extension);
    }

    @Override
    public String getLibraryFolder() {
        return "Books";
    }
}
