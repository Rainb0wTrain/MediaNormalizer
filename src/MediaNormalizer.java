import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Set;

public class MediaNormalizer {

    private static final Set<String> MEDIA_EXTENSIONS = Set.of("mkv", "mp4", "avi", "m4v", "mov");

    private final FilenameParser parser;
    private final FileRenamer renamer;

    public MediaNormalizer(FilenameParser parser, FileRenamer renamer) {
        this.parser = parser;
        this.renamer = renamer;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java MediaNormalizer <libraryRoot> <inputDir>");
            System.exit(1);
        }

        Path libraryRoot = Path.of(args[0]);
        Path inputDir = Path.of(args[1]);

        FilenameParser parser = new FilenameParser();
        FileRenamer renamer = new FileRenamer(libraryRoot, false, true);
        MediaNormalizer app = new MediaNormalizer(parser, renamer);

        app.processDir(inputDir);
    }

    public void processFile(Path source) {
        try {
            String filename = source.getFileName().toString();
            MediaInfo info = parser.parse(filename);
            Path target = renamer.rename(source, info);
            System.out.println("OK: " + source + " -> " + target);
        } catch (ParseException e) {
            System.err.println("PARSE FAIL: " + source + " - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO FAIL: " + source + " - " + e.getMessage());
        }
    }

    public void processDir(Path dir) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                String name = file.getFileName().toString();
                int dot = name.lastIndexOf('.');
                if (Files.isRegularFile(file)
                        && dot > 0
                        && MEDIA_EXTENSIONS.contains(name.substring(dot + 1).toLowerCase())) {
                    processFile(file);
                }
            }
        }
    }
}