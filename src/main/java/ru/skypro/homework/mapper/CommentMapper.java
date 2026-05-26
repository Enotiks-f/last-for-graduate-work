package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.comment.CommentDto;
import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    /**
     * Маппинг сущности Comment в CommentDto.
     * Требует, чтобы связи author и ad были загружены (например, через JOIN FETCH или внутри транзакции).
     */
    public CommentDto toDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentDto dto = new CommentDto();
        dto.setPk(comment.getId());
        dto.setText(comment.getText());

        // Конвертация Instant -> Long (миллисекунды)
        if (comment.getCreatedAt() != null) {
            dto.setCreatedAt(comment.getCreatedAt().toEpochMilli());
        }

        // Данные автора (из связи User)
        User author = comment.getAuthor();
        if (author != null) {
            dto.setAuthor(author.getId());
            dto.setAuthorFirstName(author.getFirstName());
            // Предполагаем, что у User есть поле image (URL аватара)
            dto.setAuthorImage(author.getImage());
        }

        return dto;
    }

    /**
     * Преобразование списка Comment в список CommentDto.
     */
    public List<CommentDto> toDtoList(List<Comment> comments) {
        if (comments == null) {
            return null;
        }
        return comments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Создание новой сущности Comment из CreateOrUpdateCommentDto.
     * Поля author, ad, createdAt не заполняются – они будут установлены в сервисе.
     */
    public Comment toEntity(CreateOrUpdateCommentDto dto) {
        if (dto == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setText(dto.getText());
        // createdAt будет проставлен в @PrePersist, можно не трогать
        return comment;
    }

    /**
     * Обновление существующего комментария из DTO.
     */
    public void updateEntity(CreateOrUpdateCommentDto dto, Comment comment) {
        if (dto == null || comment == null) {
            return;
        }
        comment.setText(dto.getText());
        // Остальные поля (автор, ad, дата) не обновляются
    }

    /**
     * Формирование CommentsDto (пагинированный ответ) из списка комментариев.
     */
    public CommentsDto toCommentsDto(List<Comment> comments) {
        List<CommentDto> commentDtos = toDtoList(comments);
        Integer count = commentDtos != null ? commentDtos.size() : 0;
        return new CommentsDto(count, commentDtos);
    }
}
