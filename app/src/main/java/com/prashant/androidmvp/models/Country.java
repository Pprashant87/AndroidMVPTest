
package com.prashant.androidmvp.models;
/**
 * @author : Prashant P
 * @Name: Country
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 *
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<Row> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

}
