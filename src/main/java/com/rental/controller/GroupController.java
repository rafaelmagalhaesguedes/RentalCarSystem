package com.rental.controller;

import com.rental.controller.dto.customer.CustomerDto;
import com.rental.controller.dto.group.GroupCreationDto;
import com.rental.controller.dto.group.GroupDto;
import com.rental.entity.Customer;
import com.rental.entity.Group;
import com.rental.service.GroupService;
import com.rental.service.exception.GroupNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
@Validated
public class GroupController {

  private final GroupService groupService;

  @Autowired
  public GroupController(GroupService groupService) {
    this.groupService = groupService;
  }

  @GetMapping("/{id}")
  public GroupDto getGroupById(@PathVariable UUID id) throws GroupNotFoundException {
    return GroupDto.fromEntity(
        groupService.getGroupById(id)
    );
  }

  @GetMapping
  public List<GroupDto> getAllGroups(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {
    List<Group> paginatedCustomers = groupService.getAllGroups(pageNumber, pageSize);
    return paginatedCustomers.stream()
        .map(GroupDto::fromEntity)
        .toList();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GroupDto createGroup(@RequestBody @Valid GroupCreationDto groupCreationDto) {
    return GroupDto.fromEntity(
        groupService.createGroup(groupCreationDto.toEntity())
    );
  }

  @PutMapping("/{id}")
  public GroupDto updateGroup(@RequestBody @Valid GroupCreationDto groupCreationDto, @PathVariable UUID id) throws GroupNotFoundException {
    return GroupDto.fromEntity(
        groupService.updateGroup(groupCreationDto.toEntity(), id)
    );
  }

  @DeleteMapping("/{id}")
  public GroupDto deleteGroup(@PathVariable UUID id) throws GroupNotFoundException {
    return GroupDto.fromEntity(
        groupService.deleteGroup(id)
    );
  }
}