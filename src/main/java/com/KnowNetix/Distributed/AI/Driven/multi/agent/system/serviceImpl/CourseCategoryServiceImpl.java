package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception.ErrorCodes;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception.ResourceNotFoundException;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Course;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.CourseCategory;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.CourseCategoryRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.CourseCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final UserRepository userRepository;

    private  final CourseCategoryRepository courseCategoryRepository;


    @Override
    public Long createCourseCategory(CourseCategory courseCategory, Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if(!userOptional.isPresent()){
            throw new ResourceNotFoundException("User not found");
        }

        CourseCategory course = CourseCategory.builder()
                .name(courseCategory.getName())
                .build();

        CourseCategory savedCourseCategory = courseCategoryRepository.save(course);
        return savedCourseCategory.getId();
    }

    @Override
    public List<CourseCategory> getCourseCategories() {
        return courseCategoryRepository.findAll();
    }

    @Override
    public CourseCategory getCourseCategory(Long courseCategoryId) {

        Optional<CourseCategory> courseCategory = courseCategoryRepository.findById(courseCategoryId);
        if(!courseCategory.isPresent()){
            throw  new ResourceNotFoundException(ErrorCodes.RESOURCE_NOT_FOUND.getMessage());
        }
        return courseCategory.get();
    }

    @Override
    public void updateCourseCategory(Long courseCategoryId, CourseCategory courseCategory) {
        Optional<CourseCategory> courseOptional = courseCategoryRepository.findById(courseCategoryId);

        if(!courseOptional.isPresent()){
            throw new ResourceNotFoundException("CourseCategoryId  not found");
        }

        courseOptional.get().setName(courseCategory.getName());
        courseCategoryRepository.save(courseOptional.get());
    }

    @Override
    public void deleteCourseCategory(Long courseCategoryId) {
        Optional<CourseCategory> courseOptional = courseCategoryRepository.findById(courseCategoryId);

        if(!courseOptional.isPresent()){
            throw new ResourceNotFoundException("CourseCategoryId  not found");
        }
        courseCategoryRepository.deleteById(courseCategoryId);
    }
}
