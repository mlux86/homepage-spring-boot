package de.l0x.homepage.db.textcontent;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = TextContent.TABLE_NAME)
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class TextContent
{

    protected static final String TABLE_NAME = "text_content";

    @Id
    @Getter
    @NonNull
    private String key;

    @Getter
    @NonNull
    private String text;

}
