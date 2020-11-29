package de.l0x.homepage.db.textcontent;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TextContentRepository extends CrudRepository<TextContent, String>
{

    Optional<TextContent> findByKey(String key);

}
