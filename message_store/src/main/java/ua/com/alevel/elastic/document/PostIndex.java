package ua.com.alevel.elastic.document;

import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
//@ToString
@Document(indexName = "postindex")
public class PostIndex {

    @Id
    private String id;

    @Field(name = "message", type = FieldType.Text)
    private String message;
}
