//Đây là một lớp controller tổng quát, cho phép bạn xử lý các thao tác CRUD (Create, Read, Update, Delete) cho nhiều loại tài nguyên khác nhau một cách đồng nhất.
package hcmute.projectBackend2024.generic;

import com.fasterxml.jackson.databind.JsonNode;

import hcmute.projectBackend2024.constant.AppConstants;
import hcmute.projectBackend2024.dto.ListResponse;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@Setter
@Scope("prototype")
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class GenericController<D, R> {

    private CrudService<Long, D, R> crudService;
    private Class<D> requestType;

    public ResponseEntity<ListResponse<R>> getAllResources(
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sort", defaultValue = AppConstants.DEFAULT_SORT) String sort,
            @RequestParam(name = "filter", required = false) @Nullable String filter,
            @RequestParam(name = "search", required = false) @Nullable String search,
            @RequestParam(name = "all", required = false) boolean all
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(crudService.findAll(page, size, sort, filter, search, all));
    }

    public ResponseEntity<R> getResource(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(crudService.findById(id));
    }

    public ResponseEntity<R> createResource(@RequestBody JsonNode request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crudService.save(request, requestType));
    }

    public ResponseEntity<R> updateResource(@PathVariable("id") Long id, @RequestBody JsonNode request) {
        return ResponseEntity.status(HttpStatus.OK).body(crudService.save(id, request, requestType));
    }

    public ResponseEntity<Void> deleteResource(@PathVariable("id") Long id) {
        crudService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<Void> deleteResources(@RequestBody List<Long> ids) {
        crudService.delete(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
