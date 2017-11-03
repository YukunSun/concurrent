package 并发的设计模式;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/8/8
 */
public class Data {
    private Integer id;
    private String name;

    public Data(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
