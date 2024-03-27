package com.doanchung.springshop.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") //o can thiet luc nay lam
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    /**
     * Explain annotation
     * @Entity: Đánh dấu lớp là một entity trong JPA, tức là các đối tượng của lớp này sẽ được ánh xạ vào các bản ghi trong một bảng của cơ sở dữ liệu quan hệ.
     * @Table(name = "categories"): Xác định tên của bảng cơ sở dữ liệu mà entity này được ánh xạ đến. Trong trường hợp này, entity sẽ được ánh xạ vào bảng có tên là "categories".
     * @Id: Đánh dấu trường là khóa chính của entity.
     * @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định chiến lược sinh giá trị cho khóa chính. GenerationType.IDENTITY chỉ ra rằng cơ sở dữ liệu sẽ tự động gán một giá trị khóa chính duy nhất cho mỗi entity mới.
     * @Column(name = "id"): Xác định tên cột trong bảng cơ sở dữ liệu mà trường này được ánh xạ đến. Trong trường hợp này, nó xác định tên cột cho trường id.
     * @Column(name = "name", nullable = false): Xác định tên cột cho trường name trong bảng cơ sở dữ liệu, và cũng xác định rằng cột này không thể chứa giá trị null.
     *
     */
}
