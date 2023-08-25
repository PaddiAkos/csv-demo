package hu.demo.csv.repository;

import hu.demo.csv.domain.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Long>
{
    @Query(value = "select d from Data d") //JPQL
    List<Data> findEverythingJPQL();

    @Query(value = "select * from Data", nativeQuery = true) //native
    List<Data> findEverythingNative();
}
