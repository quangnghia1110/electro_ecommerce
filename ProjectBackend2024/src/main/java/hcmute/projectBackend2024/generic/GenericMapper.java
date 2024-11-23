package hcmute.projectBackend2024.generic;

import org.mapstruct.MappingTarget;
import java.util.List;
public interface GenericMapper<E, D, R> {
    //Chuyển đối dto thành entity
	E requestToEntity(D request);
    //Chuyển đổi entity thành response
	R entityToResponse(E entity);
    //Chuyển đổi danh sách DTO thành danh sách Entity
	List<E> requestToEntity(List<D> requests);
	//Chuyển đổi danh sách Entity thành danh sách Response
    List<R> entityToResponse(List<E> entities);
    //Cập nhật đối tượng entity dựa trên dto
    E partialUpdate(@MappingTarget E entity, D request);
}
//Ví dụ
//public StudentResponseDTO createStudent(StudentRequestDTO request) {
//    StudentEntity studentEntity = studentMapper.requestToEntity(request);
//    studentEntity = studentRepository.save(studentEntity);
//    return studentMapper.entityToResponse(studentEntity);
//}
