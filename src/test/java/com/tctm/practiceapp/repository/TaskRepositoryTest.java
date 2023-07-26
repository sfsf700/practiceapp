package com.tctm.practiceapp.repository;

import com.tctm.practiceapp.entity.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;
    @Test
    void insert() {
        // テストデータの準備
        String title = "Task Title";
        String content = "Task Content";
        String period = "2023-07-18";
        String importance = "高";
        int manager_id = 2;

        // テスト対象のメソッド呼び出し
        taskRepository.insert(title, content, period, importance, manager_id);

        // 挿入後のデータを取得して検証
        List<TaskEntity> tasks = taskRepository.findAll();
        assertNotNull(tasks);
        assertEquals(1, tasks.size());

        TaskEntity insertedTask = tasks.get(0);
        assertEquals(title, insertedTask.getTitle());
        assertEquals(content, insertedTask.getContent());
        assertEquals(period, insertedTask.getPeriod());
        assertEquals(importance, insertedTask.getImportance());
        assertEquals(manager_id, insertedTask.getManager_id());
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}