package kr.co.annotation.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import kr.co.annotation.member.dto.MemberDTO;


public interface MemberDAO {

	public List<MemberDTO> selectAllMemberList() throws DataAccessException;
	public int insertMember(MemberDTO memberDTO) throws DataAccessException;
	public int deleteMember(String id) throws DataAccessException;
}
