package br.com.rangeldev.api_restful_spring_railway.controller.dto;

import java.util.List;

import br.com.rangeldev.api_restful_spring_railway.domain.entitties.User;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public record UserDto(
    Long id,
    String name,
    AccountDto account,
    CardDto card,
    List<FeatureDto> features,
    List<NewsDto> news)  {
        
        public UserDto(User entity) {
            this(
                    entity.getId(),
                    entity.getName(),
                    ofNullable(entity.getAccount()).map(AccountDto::new).orElse(null),
                    ofNullable(entity.getCard()).map(CardDto::new).orElse(null),
                    ofNullable(entity.getFeatures()).orElse(emptyList()).stream().map(FeatureDto::new).collect(toList()),
                    ofNullable(entity.getNews()).orElse(emptyList()).stream().map(NewsDto::new).collect(toList())
            );
        }
        public User toEntity() {
            User _entity = new User();
            _entity.setId(this.id);
            _entity.setName(this.name);
            _entity.setAccount(ofNullable(this.account).map(AccountDto::toEntity).orElse(null));
            _entity.setCard(ofNullable(this.card).map(CardDto::toEntity).orElse(null));
            _entity.setFeatures(ofNullable(this.features).orElse(emptyList()).stream().map(FeatureDto::toEntity).collect(toList()));
            _entity.setNews(ofNullable(this.news).orElse(emptyList()).stream().map(NewsDto::toEntity).collect(toList()));
            return _entity;
        }
    
}
