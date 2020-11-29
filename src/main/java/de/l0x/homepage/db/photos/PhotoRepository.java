package de.l0x.homepage.db.photos;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PhotoRepository extends PagingAndSortingRepository<Photo, Integer>
{

    Optional<Photo> findByFileName(String fileName);

}
