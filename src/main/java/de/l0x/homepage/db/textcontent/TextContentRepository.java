package de.l0x.homepage.db.textcontent;

import org.springframework.data.repository.CrudRepository;

public interface TextContentRepository extends CrudRepository<TextContent, String>
{

    TextContent findByKey(String key);

}
