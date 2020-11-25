package de.l0x.homepage.db.photos;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhotoRepository extends PagingAndSortingRepository<Photo, Integer>
{

    Photo findByFileName(String fileName);

}
