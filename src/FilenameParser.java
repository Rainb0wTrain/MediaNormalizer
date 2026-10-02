
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilenameParser{

    private static final Pattern TV_PATTERN = Pattern.compile("(?i)(.+?)[\\s._-]+S(\\d{1,2})E(\\d{1,2})");
    private static final Pattern MOVIE_PATTERN = Pattern.compile("(?i)(.+?)[\\s._(-]+\\(?((?:19|20)\\d{2})\\)?");


    public MediaInfo parse(String fileName)throws ParseException{
        String extension = fileName.substring(fileName.lastIndexOf('.' )+ 1);

        Matcher tvMatch = TV_PATTERN.matcher(fileName);
        if(tvMatch.find()){
            String title = tvMatch.group(1);
            title = cleanTitle(title);
            int season = Integer.parseInt(tvMatch.group(2));
            int episode = Integer.parseInt(tvMatch.group(3));

            return new TVShowInfo(title, extension, season, episode);
        }

        Matcher movieMatch = MOVIE_PATTERN.matcher(fileName);
        if(movieMatch.find()){
            String title = movieMatch.group(1);
            title = cleanTitle(title);
            int year = Integer.parseInt(movieMatch.group(2));

            return new MovieInfo(title, extension, year);
        }
            throw new ParseException("Could not parse: " + fileName);
    }

    private String cleanTitle(String title){

        return title = title.replaceAll("[._]", " ").trim();
    }







}