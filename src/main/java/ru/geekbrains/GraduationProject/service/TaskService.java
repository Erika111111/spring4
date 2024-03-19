package ru.geekbrains.GraduationProject.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.GraduationProject.model.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
@Service
public class TaskService {

        private List<Task> tasks = new ArrayList<>();

        /**
         * метод получения всех задач
         */
        public  List<Task> getALLTask(){
            return tasks;
        }

        /**
         * метод получения определенной задачи по ее id
         * @param uuid
         * @return метод findFirst выводит первый попавший под параметры элемент
         */
        public Task getTask(UUID uuid){
            return tasks.stream().filter(t -> t.getId().equals(uuid)).findFirst().orElse(null);
        }

        /**
         * метод добавления новой задачи
         */
        public void addTask(Task task){
            task.setStatus(Task.Status.TO_DO); // добавление к каждой новой задачи статуса автоматически
            tasks.add(task);
        }

    /**
     * метод добавления измененной задачи в список
     * @param task
     */
    public void saveTask(Task task){
            tasks.add(task);
        }

        /**
         * метод удаления задачи
         */
        public void deleteTask(UUID uuid){
            tasks.removeIf(t -> t.getId().equals(uuid)); // удаление по id, сравниваем id с id из нашей коллекции и удаляем первый попавший элемент
        }

    /**
     * метод сортировки задач по статусу
     * @return
     */
    public List<Task> getTasksSortByStatus(){
            List<Task> sortedTasks = new ArrayList<>(tasks);
            sortedTasks.sort(Comparator.comparing(Task::getStatus));
            return sortedTasks;
        }

        /**
         * обновление задачи в списке
         */
        public Task updateTask(UUID uuid, Task task){
            Task task1 = getTask(uuid);
            if(task1 != null){
                task1.setDescription(task.getDescription());
                task1.setName(task.getName());
                task1.setStatus(task.getStatus());
            }
            return task1;
        }

    }

