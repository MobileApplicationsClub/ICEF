package bits.mac.icef_2018.Lists;

import com.macbitsgoa.icef.R;

/**
 * Created by aayush on 18/12/17.
 */

@SuppressWarnings("ALL")
public class MainList {

    //defining variables in an item
    private String title;
    private String description;
    private int background;
    private String id;


    //Constructor to add top most item
    public MainList() {
        title = "Tides of Time";
        description = "Categories";
        id = "EVENT";
        this.background = R.drawable.countdown;
    }


    public MainList(String title, String description, int background, String id) {
        this.title = title;
        this.background = background;
        this.description = description;
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
