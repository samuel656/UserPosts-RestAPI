package com.webservices.restful_web_services.repository;

import com.webservices.restful_web_services.model.Post;
import com.webservices.restful_web_services.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

}
