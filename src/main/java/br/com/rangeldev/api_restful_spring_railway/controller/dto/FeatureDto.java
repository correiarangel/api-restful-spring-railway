package br.com.rangeldev.api_restful_spring_railway.controller.dto;

import br.com.rangeldev.api_restful_spring_railway.domain.entitties.Feature;

public record FeatureDto(Long id, String icon, String description) {
public FeatureDto(Feature entity){
    this(entity.getId(), entity.getIcon(), entity.getDescription());
}
    public Feature toEntity() {
        Feature _entity = new Feature();
        _entity.setId(this.id);
        _entity.setIcon(this.icon);
        _entity.setDescription(this.description);
        return _entity;
    }
}
