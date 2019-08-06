package dashboard;



public class ImgTable {

    private String  index = null;
    private String  filename = null;
    private String  filesize = null;
    private String  imgViews;


    public ImgTable(String index, String filename, String filesize, String imgViews) {
        this.index = index;
        this.filename = filename;
        this.filesize = filesize;
        this.imgViews = imgViews;

    }



    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }





    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {

        this.filename = filename;
    }



    public String getFilesize() {
        return filesize;

    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }



    public String getImgViews() {
        return imgViews;
    }

    public void setImgViews(String imgViews) {
        this.imgViews = imgViews;
    }

}