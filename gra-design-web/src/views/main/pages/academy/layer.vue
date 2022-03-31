<template>
  <Layer :layer="layer" @confirm="submit" ref="layerDom">
    <el-form :model="form" :rules="rules" ref="ruleForm" label-width="120px" style="margin-right:30px;">
      <el-form-item label="姓名：" prop="name">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="手机号：" prop="phone">
        <el-input v-model="form.phone" oninput="value=value.replace(/[^\d]/g,'')" placeholder="只能输入正整数"></el-input>
      </el-form-item>
      <el-form-item label="学号：" prop="num">
        <el-input v-model="form.num" oninput="value=value.replace(/[^\d]/g,'')" placeholder="只能输入正整数"></el-input>
      </el-form-item>
      <el-form-item label="邮箱：" prop="mail">
        <el-input v-model="form.mail" oninput="value=value.replace(/[^\d]/g,'')" placeholder="只能输入正整数"></el-input>
      </el-form-item>
      <el-form-item label="学校：" prop="college">
        <el-input v-model="form.college" placeholder="请输入学校"></el-input>
      </el-form-item>
			<el-form-item label="目的城市：" prop="city">
			  <el-select v-model="form.city" placeholder="请选择城市" clearable>
					<el-option v-for="item in selectData" :key="item.value" :label="item.label" :value="item.value"></el-option>
				</el-select>
			</el-form-item>
      <el-form-item label="家庭住址：" prop="address">
        <el-input v-model="form.address" placeholder="请输入家庭住址"></el-input>
      </el-form-item>
      <el-form-item label="上学时间：" prop="start">
        <el-input v-model="form.start" placeholder="请输入上学时间"></el-input>
      </el-form-item>
      <el-form-item label="毕业时间：" prop="end">
        <el-input v-model="form.end" placeholder="请输入毕业时间"></el-input>
      </el-form-item>
      <el-form-item label="家庭住址：" prop="info">
        <el-input v-model="form.info" placeholder="请输入备注"></el-input>
      </el-form-item>
      <el-form-item label="年制：" prop="year">
        <el-radio-group v-model="form.year">
          <el-radio v-for="item in radioData" :key="item.value" :label="item.value">{{ item.label }}</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
  </Layer>
</template>

<script>
import { defineComponent, ref } from 'vue'
import { add, update } from '@/api/table'
import { selectData, radioData } from './enum'
import Layer from '@/components/layer/index.vue'
export default defineComponent({
  components: {
    Layer
  },
  props: {
    layer: {
      type: Object,
      default: () => {
        return {
          show: false,
          title: '',
          showButton: true
        }
      }
    }
  },
  setup(props, ctx) {
    const ruleForm= ref(null)
    const layerDom = ref(null)
    let form = ref({
      name: ''
    })
    const rules = {
      name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
      number: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
      num: [{ required: true, message: '请输入学号', trigger: 'blur' }],
      mail: [{ required: true, message: '请输入邮箱', trigger: 'blur' }],
      college: [{ required: true, message: '请输入学校', trigger: 'blur' }],
      city: [{ required: true, message: '请选择城市', trigger: 'blur' }],
      address: [{ required: true, message: '请输入家庭住址', trigger: 'blur' }],
      start: [{ required: true, message: '请输入上学时间', trigger: 'blur' }],
      end: [{ required: true, message: '请输入毕业时间', trigger: 'blur' }],
      info: [{ required: true, message: '请输入备注信息', trigger: 'blur' }],
      // identity: [{ required: true, message: '请选择身份', trigger: 'blur' }],
      year: [{ required: true, message: '请选择年制', trigger: 'blur' }]
      // date: [{ required: true, message: '请选择年制', trigger: 'blur' }]
    }
    init()
    function init() { // 用于判断新增还是编辑功能
      if (props.layer.row) {
        form.value = JSON.parse(JSON.stringify(props.layer.row)) // 数量量少的直接使用这个转
      } else {

      }
    }
    return {
      form,
      rules,
      layerDom,
      ruleForm,
      selectData,
      radioData
    }
  },
  methods: {
    submit() {
      if (this.ruleForm) {
        this.ruleForm.validate((valid) => {
          if (valid) {
            let params = this.form
            if (this.layer.row) {
              this.updateForm(params)
            } else {
              this.addForm(params)
            }
          } else {
            return false;
          }
        });
      }
    },
    // 新增提交事件
    addForm(params) {
      add(params)
      .then(res => {
        this.$message({
          type: 'success',
          message: '新增成功'
        })
        this.$emit('getTableData', true)
        this.layerDom && this.layerDom.close()
      })
    },
    // 编辑提交事件
    updateForm(params) {
      update(params)
      .then(res => {
        this.$message({
          type: 'success',
          message: '编辑成功'
        })
        this.$emit('getTableData', false)
        this.layerDom && this.layerDom.close()
      })
    }
  }
})
</script>

<style lang="scss" scoped>
  
</style>