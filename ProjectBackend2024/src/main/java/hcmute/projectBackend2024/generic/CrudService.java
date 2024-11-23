package hcmute.projectBackend2024.generic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import hcmute.projectBackend2024.constant.FieldName;
import hcmute.projectBackend2024.dto.ListResponse;
import hcmute.projectBackend2024.exception.ResourceNotFoundException;
import hcmute.projectBackend2024.utils.SearchUtils;
import io.github.perplexhub.rsql.RSQLJPASupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CrudService<ID, D, R> {

	// Dùng trong findAll trong GenericService và getAllResources trong GenericController
	ListResponse<R> findAll(int page, int size, String sort, String filter, String search, boolean all);
	default <E> ListResponse<R> defaultFindAll(int page, int size, String sort, String filter, String search,
			boolean all, List<String> searchFields, JpaSpecificationExecutor<E> repository,
			GenericMapper<E, D, R> mapper) {
		Specification<E> sortable = RSQLJPASupport.toSort(sort);
		Specification<E> filterable = RSQLJPASupport.toSpecification(filter);
		Specification<E> searchable = SearchUtils.parse(search, searchFields);
		Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
		Page<E> entities = repository.findAll(sortable.and(filterable).and(searchable), pageable);
		List<R> entityResponses = mapper.entityToResponse(entities.getContent());
		return new ListResponse<>(entityResponses, entities);
	}

	// Dùng trong findById trong GenericController và getResource trong GenericController
	R findById(ID id);
	default <E> R defaultFindById(ID id, JpaRepository<E, ID> repository, GenericMapper<E, D, R> mapper,
			String resourceName) {
		return repository.findById(id).map(mapper::entityToResponse)
				.orElseThrow(() -> new ResourceNotFoundException(resourceName, FieldName.ID, id));
	}

	// lưu bản ghi mới dùng trong createResource trong GenericController
	R save(D request);
	default <E> R defaultSave(D request, JpaRepository<E, ID> repository, GenericMapper<E, D, R> mapper) {
		E entity = mapper.requestToEntity(request);
		entity = repository.save(entity);
		return mapper.entityToResponse(entity);
	}

	// lưu bản ghi đã có dùng trong updateResource trong GenericController
	R save(ID id, D request);
	default <E> R defaultSave(ID id, D request, JpaRepository<E, ID> repository, GenericMapper<E, D, R> mapper,
			String resourceName) {
		return repository.findById(id).map(existingEntity -> mapper.partialUpdate(existingEntity, request))
				.map(repository::save).map(mapper::entityToResponse)
				.orElseThrow(() -> new ResourceNotFoundException(resourceName, FieldName.ID, id));
	}

	// Dùng trong GenericService và GeneralController
	void delete(ID id);
	void delete(List<ID> ids);

	// Dùng trong GeneralController
	default R save(JsonNode request, Class<D> requestType) {
		ObjectMapper mapper = new ObjectMapper();
		D typedRequest = mapper.convertValue(request, requestType);
		return save(typedRequest);
	}

	default R save(ID id, JsonNode request, Class<D> requestType) {
		ObjectMapper mapper = new ObjectMapper();
		D typedRequest = mapper.convertValue(request, requestType);
		return save(id, typedRequest);
	}
}
