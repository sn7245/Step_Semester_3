abstract class GalleryArtPiece {
    private static int count = 0;
    private final String pieceId;

    public GalleryArtPiece() {
        count++;
        pieceId = "ART-" + (1000 + count);
    }

    public abstract String describe();

    public String getPieceId() {
        return pieceId;
    }
}

class GalleryPainting extends GalleryArtPiece {
    private String title;

    public GalleryPainting(String title) {
        this.title = title;
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class GallerySculpture extends GalleryArtPiece {
    private String title;

    public GallerySculpture(String title) {
        this.title = title;
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}

public class GalleryDescriptionCards {
}