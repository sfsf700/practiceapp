package com.tctm.practiceapp.repository;

import com.tctm.practiceapp.entity.TaskEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskRepository {
    @Insert("insert into tasks (title, content, period, importance, manager_id) values (#{title}, #{content}, #{period}, #{importance}, #{manager_id})")
    void insert(String title, String content, String period, String importance, Integer manager_id);

    @Select(
            "select t.id, t.title, t.content, DATE_FORMAT(t.period, '%y/%m/%d'), t.importance, m.first_name " +
            "from tasks as t " +
            "join managers as m " +
            "on t.manager_id = m.id")
    List<TaskEntity> findAll();

    @Select("select * from tasks join managers on tasks.manager_id = managers.id where tasks.id = #{id}")
    TaskEntity findById(long id);

    @Update("UPDATE tasks SET title = #{title}, content = #{content}, period = #{period}, importance = #{importance}, manager_id = #{manager_id} WHERE id =#{id}")
    void update(long id, String title, String content, String period, String importance, int manager_id);

    @Delete("delete from tasks join managers on tasks.manager_id = manager.id where id = #{id}")
    void delete(long id);

    @Select("select t.id, t.title, t.content, DATE_FORMAT(t.period, '%y/%m/%d'), t.importance, m.first_name " +
            "from tasks as t " +
            "join managers as m " +
            "on t.manager_id = m.id " + // スペースを追加
            "where t.importance = #{importance}")
    List<TaskEntity> findWhere(@Param("importance") String importance);

}
