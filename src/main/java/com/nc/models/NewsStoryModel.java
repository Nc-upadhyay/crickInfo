package com.nc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsStoryModel {
    private String type;
    private String ImageUrl;
    private String title;
    private String heading;
    private String subHeading;
    private String time_ago;
    private String link;


}
