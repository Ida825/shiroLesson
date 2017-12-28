package cn.et.food.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

import cn.et.food.entity.Dept;
import cn.et.food.entity.DeptExample;

public interface DeptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @SelectProvider(type=DeptSqlProvider.class, method="countByExample")
    long countByExample(DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @DeleteProvider(type=DeptSqlProvider.class, method="deleteByExample")
    int deleteByExample(DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @Delete({
        "delete from dept",
        "where deptid = #{deptid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer deptid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @Insert({
        "insert into dept (deptid, dname, ",
        "pid)",
        "values (#{deptid,jdbcType=INTEGER}, #{dname,jdbcType=VARCHAR}, ",
        "#{pid,jdbcType=INTEGER})"
    })
    int insert(Dept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @InsertProvider(type=DeptSqlProvider.class, method="insertSelective")
    int insertSelective(Dept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @SelectProvider(type=DeptSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="deptid", property="deptid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="dname", property="dname", jdbcType=JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER)
    })
    List<Dept> selectByExampleWithRowbounds(DeptExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @SelectProvider(type=DeptSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="deptid", property="deptid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="dname", property="dname", jdbcType=JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER)
    })
    List<Dept> selectByExample(DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @Select({
        "select",
        "deptid, dname, pid",
        "from dept",
        "where deptid = #{deptid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="deptid", property="deptid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="dname", property="dname", jdbcType=JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER)
    })
    Dept selectByPrimaryKey(Integer deptid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @UpdateProvider(type=DeptSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Dept record, @Param("example") DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @UpdateProvider(type=DeptSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Dept record, @Param("example") DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @UpdateProvider(type=DeptSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Dept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept
     *
     * @mbg.generated Thu Dec 14 10:13:38 CST 2017
     */
    @Update({
        "update dept",
        "set dname = #{dname,jdbcType=VARCHAR},",
          "pid = #{pid,jdbcType=INTEGER}",
        "where deptid = #{deptid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Dept record);
}