package com.book.boogit.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name="user", schema = "boogit")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idx")
    private Long idx;

    @Column(name="u_id")
    private String u_id;

    @Column(name="u_pwd")
    private String u_pwd;

    @Column(name="u_nm")
    private String u_nm;

    @Column(name="u_eml_id")
    private String u_eml_id;

    @CreatedDate
    @Column(name="frst_reg_dt")
    private LocalDateTime frst_reg_dt;

    @LastModifiedDate
    @Column(name="last_upt_dt")
    private LocalDateTime last_upt_dt;

    public User() {}

    public User(Long idx, String u_id, String u_pwd, String u_nm, String u_eml_id, LocalDateTime frst_reg_dt, LocalDateTime last_upt_dt) {
        super();
        this.idx = idx;
        this.u_id = u_id;
        this.u_pwd = u_pwd;
        this.u_eml_id = u_eml_id;
        this.frst_reg_dt = frst_reg_dt;
        this.last_upt_dt = last_upt_dt;
    }






}
