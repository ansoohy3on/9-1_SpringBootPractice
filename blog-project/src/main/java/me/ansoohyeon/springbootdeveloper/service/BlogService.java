package me.ansoohyeon.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.ansoohyeon.springbootdeveloper.domain.Article;
import me.ansoohyeon.springbootdeveloper.dto.AddArticleRequest;
import me.ansoohyeon.springbootdeveloper.dto.UpdateArticleRequest;
import me.ansoohyeon.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메소드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

   // 데이터베이스에 저장되어 있는 모든 글을 가져오는 메소드
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    // 데이터베이스에 저장되어 있는 글 하나 가져오는 메소드
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    // 블로그 글 삭제 메소드
    public void delete(long id){
        blogRepository.deleteById(id);
    }
    
    // 블로그 글 수정 메소드
    @Transactional // 트랜잭션 메소드
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
