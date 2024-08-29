package com.example.capstone_3.Repository;
import com.example.capstone_3.Model.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepository extends JpaRepository<Designer, Integer> {

    Designer findDesignerById(Integer id);
}
