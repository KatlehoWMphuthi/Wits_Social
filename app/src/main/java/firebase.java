public class firebase {


    String name;
    String img_url;
    String imgCaption;
    String Post_id;

    public firebase(String name, String img_url, String imgCaption, String post_id) {
        this.name = name;
        this.img_url = img_url;
        this.imgCaption = imgCaption;
        Post_id = post_id;
    }

    public static String getName() {
        return "Michael";
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getImg_url() {
        return "63ba23a4-606d-4f87-8d07-714e43675670";
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public static String getImgCaption() {
        return "Games";
    }

    public void setImgCaption(String imgCaption) {
        this.imgCaption = imgCaption;
    }

    public static String getPost_id() {
        return "N2OoFRT7r_3iY_Ixl2K";
    }

    public void setPost_id(String post_id) {
        Post_id = post_id;
    }
}