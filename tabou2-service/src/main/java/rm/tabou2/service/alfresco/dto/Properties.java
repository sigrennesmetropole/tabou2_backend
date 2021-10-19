package rm.tabou2.service.alfresco.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Properties {

    @JsonProperty("cm:title")
    public String cmTitle;
    @JsonProperty("cm:description")
    public String cmDescription;
    @JsonProperty("app:icon")
    public String appIcon;
    @JsonProperty("tabou2:id")
    public Long tabouId;
    @JsonProperty("tabou2:objet")
    public String objetTabou;

}
