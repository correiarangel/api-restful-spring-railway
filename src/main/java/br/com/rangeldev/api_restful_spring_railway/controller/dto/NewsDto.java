package br.com.rangeldev.api_restful_spring_railway.controller.dto;

import br.com.rangeldev.api_restful_spring_railway.domain.entitties.News;

public record NewsDto(Long id, String icon, String description) {
    public NewsDto(News entity) {
        this(entity.getId(), entity.getIcon(), entity.getDescription());
    }
    public News toEntity() {
        News _entity = new News();
        _entity.setId(this.id);
        _entity.setIcon(this.icon);
        _entity.setDescription(this.description);
        return _entity;
    }
}
