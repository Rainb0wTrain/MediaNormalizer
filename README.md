# MediaNormalizer

A Java tool that parses inconsistent media filenames and renames files
into a predictable directory structure for Jellyfin libraries.

## Problem

Media files arrive with wildly inconsistent naming. Jellyfin matches
metadata by filename and directory structure, so anything unparseable
shows up in the library as an unidentified file. This tool reads a
directory, extracts title, year, season, and episode from each
filename, and renames files into a layout Jellyfin can match.

## Design

- `MediaInfo` base type with `MovieInfo`, `TVShowInfo`, `AnimeInfo`,
  and `BookInfo` subclasses
- `FilenameParser` uses regex to extract metadata, throwing a custom
  `ParseException` when a filename cannot be parsed
- `FileRenamer` handles the filesystem operations
- `MediaNormalizer` walks a directory, filters to media extensions,
  and coordinates the parse and rename

## Usage

```
javac -d out src/*.java
java -cp out MediaNormalizer <libraryRoot> <inputDir>
```
