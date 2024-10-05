package ru.nikita.library.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "watch")
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 2, max = 50, message = "Название не меньше 2, и не больше 50")
    private String name;

    @Column(name = "series")
    @Min(value = 0, message = "Кол-во серий должно быть больше 0")
    private int series;

    @Column(name = "seasons")
    @Min(value = 0, message = "Кол-во сезонов должно быть больше 0")
    private int seasons;

    public Watch() {

    }

    public Watch(String name, int series, int seasons) {
        this.name = name;
        this.series = series;
        this.seasons = seasons;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    @Override
    public String toString() {
        return "Watch [id=" + id + ", name=" + name + ", series=" + series + ", seasons=" + seasons + "]";
    }
}
