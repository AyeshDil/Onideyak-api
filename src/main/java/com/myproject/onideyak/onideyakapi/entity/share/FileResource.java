package com.myproject.onideyak.onideyakapi.entity.share;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class FileResource {
    @Column(name = "directory", length = 755)
    private String directory;
    @Column(name = "hash", length = 755)
    private String hash;
    @Column(name = "resource_url", length = 755)
    private String resourceUrl;
    @Column(name = "file_name", length = 755)
    private String fileName;
}
